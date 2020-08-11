package com.rock.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vikas Verma
 */
public class JasperData {

	public List getData() {

		List<JasperDataBean> dataList = new ArrayList<JasperDataBean>(0);

		try {

			for (int i = 1; i < 25; i++) {
				JasperDataBean objBean = new JasperDataBean();

				objBean.setAddDate("2013-07-" + i);
				objBean.setName("user_" + i);
				objBean.setEmail("user_" + i + "@abcd.com");
				objBean.setWebsite("www.user " + i + ".com");
				objBean.setSubject("this is subject");
				objBean.setMessage("this is message");
				objBean.setRecordStatus("A");
				dataList.add(objBean);
			}
		} catch (Exception ex) {
			Logger.getLogger(ex.toString());
		}

		return dataList;
	}
}