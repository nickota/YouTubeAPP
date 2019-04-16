package io.youtubeapp;

import io.youtubeapp.model.YouTubeVideo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;


class YouTubeVideoTest_testViewCountToKM {

	@Test
	void null_becomes_0() {
		YouTubeVideo video = new YouTubeVideo();
		video.setViewCount(null);
		
		assertThat(video.viewCountToKM()).isEqualTo("0");
	}
	@Test
	void negative_becomes_0() {
		YouTubeVideo video = new YouTubeVideo();
		video.setViewCount(new BigInteger("-1"));
		
		assertThat(video.viewCountToKM()).isEqualTo("0");
	}
	@Test
	void _999_remains_999() {
		YouTubeVideo video = new YouTubeVideo();
		video.setViewCount(new BigInteger("999"));
		
		assertThat(video.viewCountToKM()).isEqualTo("999");
	}
	@Test
	void _1000_becomes_1K() {
		YouTubeVideo video = new YouTubeVideo();
		video.setViewCount(new BigInteger("1000"));
		
		assertThat(video.viewCountToKM()).isEqualTo("1K");
	}
	@Test
	void _99999_becomes_999K() {
		YouTubeVideo video = new YouTubeVideo();
		video.setViewCount(new BigInteger("999999"));
		
		assertThat(video.viewCountToKM()).isEqualTo("999K");
	}
	@Test
	void _1000000_becomes_1M() {
		YouTubeVideo video = new YouTubeVideo();
		video.setViewCount(new BigInteger("1000000"));
		
		assertThat(video.viewCountToKM()).isEqualTo("1M");
	}
}