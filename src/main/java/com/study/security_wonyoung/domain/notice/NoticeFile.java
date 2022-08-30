package com.study.security_wonyoung.domain.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoticeFile {
	private int file_code;
	private int notice_code;
	private String file_name;
}