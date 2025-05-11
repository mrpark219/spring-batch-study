package me.park.springbatchstudy.job;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

@Getter
public class SimpleJobParameter {

	@Value("#{jobParameters[requestDate]}")
	private LocalDate requestDate;
}
