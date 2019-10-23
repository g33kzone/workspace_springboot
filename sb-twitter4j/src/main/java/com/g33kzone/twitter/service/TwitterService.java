package com.g33kzone.twitter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TwitterService {

	public static Twitter getTwitterInstance() {

//		ConfigurationBuilder cb = new ConfigurationBuilder();
//		cb.setDebugEnabled(true).setOAuthConsumerKey("faNRCRJsCUFOFZKPZDFVBtbgb")
//				.setOAuthConsumerSecret("OO3ecsDJobaVYS6A11xpodw1yvS9n0GhLF1EOxlM4Xp1SIDT6t")
//				.setOAuthAccessToken("855344670444068864-vMWXjwyXjHuxa6oVeFAZ5In7efEW4e0")
//				.setOAuthAccessTokenSecret("pmXCLLbWbm3S0S6Ba1fjYTcHKE0kzYKJFt793Rzer1b8e");
//
//		TwitterFactory tf = new TwitterFactory(cb.build());
//		Twitter twitter = tf.getInstance();

		Twitter twitter = TwitterFactory.getSingleton();

		return twitter;
	}

	public List<String> searchTweets() throws TwitterException {
		Twitter twitter = getTwitterInstance();
		Query query = new Query("#100daysofcode");

		QueryResult result = twitter.search(query);
		List<Status> statuses = result.getTweets();

		return statuses.stream().map(item -> item.getText()).collect(Collectors.toList());

	}

	public void streamFeed() {

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
				System.out.println(status.getUser().getName() + " : " + status.getText());
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
			}

		};
		
		TwitterStream twitterStream = TwitterStreamFactory.getSingleton();
		twitterStream.addListener(listener);
		twitterStream.sample();
	}
}
