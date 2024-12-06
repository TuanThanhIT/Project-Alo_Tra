package vn.iotstar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iotstar.entity.Rate;
import vn.iotstar.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipperModel {
    private int shipID;
    private Rate rate;
    private int deliverTimes;
	private Boolean isEdit = false;

}
