package world.lixiang.travels.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Province {
    private Integer id ;
    private String name ;
    private String tags;
    private Integer placecounts;


}
