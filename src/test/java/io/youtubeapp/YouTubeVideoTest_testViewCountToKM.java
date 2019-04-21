package io.youtubeapp;

import io.youtubeapp.model.YouTubeVideo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class YouTubeVideoTest_testViewCountToKM {

	@ParameterizedTest
	@CsvSource({
		"null,'0'",
		"-1, '0'",
		"0, '0'",
		"999,'999'",
		"1000,'1K'",
		"999999,'999K'",
		"1000000,'1M'"
	})
	void testViewCountToKM(BigInteger num, String str) {
		YouTubeVideo video = new YouTubeVideo();
		video.setViewCount(num);
		
		assertThat(video.viewCountToKM()).isEqualTo(str);
	}
}