package ru.romanov.StoreTZ.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityErrorResponse {
    private String message;
    private long timestamp;
}
