package reco.common.to.es;

import lombok.Data;

/**
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
@Data
public class QEsModel {
    private Long id;
    private String title;
    private String answer;
    private String typeName;
}