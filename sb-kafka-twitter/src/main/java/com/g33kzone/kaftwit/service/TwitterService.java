package com.g33kzone.kaftwit.service;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Service
public class TwitterService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value(value = "${g33kzone.topic.name}")
	String kafkaTopic;

	@Autowired
	private KafkaStreamService kafkaStreamService;

	public static Twitter getTwitterInstance() {

		Twitter twitter = TwitterFactory.getSingleton();

		return twitter;
	}

	public List<String> searchTweets(String queryString) throws TwitterException {
		Twitter twitter = getTwitterInstance();

		Query query = new Query(queryString);

		QueryResult result = twitter.search(query);
		List<Status> statuses = result.getTweets();

		return statuses.stream().map(item -> item.getPlace().getCountry().toString()).collect(Collectors.toList());

	}

	public void streamFeed(String queryString) throws InterruptedException {

		LinkedBlockingQueue<Status> queue = new LinkedBlockingQueue<Status>(1000);

		StatusListener listener = new StatusListener() {

			@Override
			public void onException(Exception e) {
				e.printStackTrace();
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg) {
				System.out.println("Got a status deletion notice id:" + arg.getStatusId());
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				System.out.println("Got stall warning:" + warning);
			}

			@Override
			public void onStatus(Status status) {
				queue.offer(status);

				kafkaStreamService.sendToKafka(status);
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
			}

		};

		TwitterStream twitterStream = TwitterStreamFactory.getSingleton();
		twitterStream.addListener(listener);

		FilterQuery query = new FilterQuery().track(queryString);
		query.locations(
				new double[][] { new double[] { 19.0760, 72.8777 },new double[] { 28.7041, 77.1025} });
		twitterStream.filter(query);

		Thread.sleep(5000);
//		twitterStream.sample();

//		twitterStream.shutdown();
	}
}
