package org.unilever.fileupload.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class UserLocationData {

    @CsvBindByName
    private long id;
    @CsvBindByName
    private String lastName;
    @CsvBindByName
    private String location;
    @CsvBindByName
    private String outletName;
    @CsvBindByName
    private String outletType;
}
