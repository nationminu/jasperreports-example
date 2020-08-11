package com.rock.example;

import com.rock.example.JasperData;
import com.rock.example.JasperDataBean;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Vikas Verma
 */
//@WebServlet("JasperTest")
public class JasperTest extends HttpServlet {

	private String DOWNLOAD_FILE_NAME = "REPORT.pdf";
	private String FILE_TYPE = "application/pdf";

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try {
			generateReport(request, response);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	private void generateReport(HttpServletRequest request, HttpServletResponse response) {

		List<JasperDataBean> dataList;
		String reportPath;
		OutputStream outStream;
		JasperReport jasperReport;
		JasperDesign jasperDesign;
		JRDataSource reportSource;
		JasperData jasperData;
		String logoFilePath;
		Map reportParameters;

		try {
			reportPath = request.getServletContext().getRealPath("reports") + "/JasperReport.jrxml";
			logoFilePath = request.getServletContext().getRealPath("reports") + "/logo.png";

			/**
			 * Set report parameters
			 */
			reportParameters = new HashMap();
			reportParameters.put("paramLogFilePath", logoFilePath);

			/**
			 * Compile Jasper Report.
			 */
			jasperDesign = JRXmlLoader.load(reportPath);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);

			/**
			 * Get report DataSource.
			 */
			jasperData = new JasperData();
			dataList = jasperData.getData();
			reportSource = new JRBeanCollectionDataSource(dataList);

			/**
			 * Get byteStream for generated Stream.
			 */
			byte[] byteStream;
			byteStream = JasperRunManager.runReportToPdf(jasperReport, reportParameters, reportSource);

			// modifies response
			response.setContentType(FILE_TYPE);
			response.setContentLength((int) byteStream.length);

			// forces download
			String headerKey = "Content-Sisposition";
			String headerValue = String.format("inline, filename=\"%s\"", DOWNLOAD_FILE_NAME);
			response.setHeader(headerKey, headerValue);

			// obtains response's output stream
			outStream = response.getOutputStream();
			outStream.write(byteStream, 0, byteStream.length);

			outStream.flush();
			outStream.close();

		} catch (JRException ex) {
			Logger.getLogger(JasperTest.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	} 
}