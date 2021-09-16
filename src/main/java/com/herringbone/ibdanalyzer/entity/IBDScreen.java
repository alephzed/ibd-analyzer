package com.herringbone.ibdanalyzer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "ibdscreens")
@TypeAlias("ibdscreen")
public class IBDScreen {

    @Id
    private UUID screenId;

    @Field("type")
    private String screenType;

    @Field("date")
    private LocalDate screenDate;

    @DBRef
    private List<IBDStock> stocks;
}
