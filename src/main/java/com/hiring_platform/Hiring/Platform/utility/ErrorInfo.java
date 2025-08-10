package com.hiring_platform.Hiring.Platform.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorInfo {
    private String errorInfo;
    private Integer errorCode;
    private LocalDateTime timeStamp;
}
