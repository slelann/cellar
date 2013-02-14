package com.mycellar.conditions.measure

import java.text.DateFormat;
import org.hibernate.StatelessSession
import org.hibernate.Transaction
import org.apache.commons.logging.LogFactory
import grails.converters.JSON


class FileCsvController {

	private static final log = LogFactory.getLog(this)
	
	static final int BUFF_SIZE = 100000;
	static final byte[] buffer = new byte[BUFF_SIZE];

    def index = {
		render(plugin: 'uploadr', template: '/index', model:[desktop: System.getProperty('user.home') + '/Desktop'])
	}

	def warning = {
		render(plugin: 'uploadr', template: '/upload/warning')
	}

	def handle = {
		def contentType	= request.getHeader("Content-Type") as String
		def fileName = request.getHeader('X-File-Name') as String
		def fileSize = request.getHeader('X-File-Size') as Long
		def name = request.getHeader('X-Uploadr-Name') as String
		def info	= session.getAttribute('uploadr')
		def savePath	= ((name && info && info.get(name) && info.get(name).path) ? info.get(name).path : "/tmp") as String
		def dir = new File(savePath)
		def file	= new File(savePath,fileName)
		int dot = 0
		def namePart = ""
		def extension = ""
		def testName = ""
		def testIterator= 1
		int status = 0
		def statusText = ""
		
		log.error("==handle==")
		
		// set response content type to json
		response.contentType = 'application/json'
		
		// does the path exist?
		if (!dir.exists()) {
			// attempt to create the path
			try {
				dir.mkdirs()
			} catch (Exception e) {
				response.setStatus(500, "could not create upload path ${savePath}")
				render([written: false, fileName: file.name] as JSON)
				return false
			}
		}

		// do we have enough space available for this upload?
		def freeSpace = dir.getUsableSpace()
		if (fileSize > freeSpace) {
			// not enough free space
			response.setStatus(500, "cannot store '${fileName}' (${fileSize} bytes), only ${freeSpace} bytes of free space left on device")
			render([written: false, fileName: file.name] as JSON)
			return false
		}

		// is the file writable?
		if (!dir.canWrite()) {
			// no, try to make it writable
			if (!dir.setWritable(true)) {
				response.setStatus(500, "'${savePath}' is not writable, and unable to change rights")
				render([written: false, fileName: file.name] as JSON)
				return false
			}
		}

		// make sure the file name is unique
		dot = fileName.lastIndexOf(".")
		namePart = (dot) ? fileName[0..dot-1] : fileName
		extension = (dot) ? fileName[dot+1..fileName.length()-1] : ""
		while (file.exists()) {
			testName = "${namePart}-${testIterator}.${extension}"
			file = new File(savePath,testName)
			testIterator++
		}

		// define input and output streams
		InputStream inStream = null
		OutputStream outStream = null

		// handle file upload
		try {
			inStream = request.getInputStream()
			outStream = new FileOutputStream(file)
			
			while (true) {
				synchronized (buffer) {
					int amountRead = inStream.read(buffer);
					if (amountRead == -1) {
						break
					}
					outStream.write(buffer, 0, amountRead)
				}
			}
			outStream.flush()

			status = 200
			statusText = "'${file.name}' upload successful!"
			
			
			
		} catch (Exception e) {
			// whoops, looks like something went wrong
			status = 500
			statusText = e.getMessage()
		} finally {
			if (inStream != null) inStream.close()
			if (outStream != null) outStream.close()
		}
		
		// make sure the file was properly written
		if (status == 200 && fileSize > file.size()) {
			// whoops, looks like the transfer was aborted!
			status = 500
			statusText = "'${file.name}' transfer incomplete, received ${file.size()} of ${fileSize} bytes"
		}
		
		// got an error of some sorts?
		if (status != 200) {
			// then -try to- delete the file
			try {
				file.delete()
			} catch (Exception e) { }
		}
		
		// Redirect to parsing method
		//redirect(action:"parseFile", params: [ file: file.name, uploadr: info])
		
		forward(action: "parsefile")
		// render json response
		response.setStatus(status, statusText)
		render([written: (status == 200), fileName: file.name, status: status, statusText: statusText] as JSON)
		
		
		}

		def delete = {
			def fileName = request.getHeader('X-File-Name')
			def name = request.getHeader('X-Uploadr-Name')
			def info	= session.getAttribute('uploadr')
			def savePath	= (name && info && info.get(name) && info.get(name).path) ? info.get(name).path : '/tmp'
			def file	= new File(savePath,fileName)
			
			if (file.exists()) {
				try {
				// delete file
				file.delete()
				
				response.sendError(200, "OK, deleted '${fileName}'")
				} catch (Exception e) {
				response.sendError(500, "could not delete '${fileName}' (${e.getMessage()}")
				}
			} else {
				response.sendError(200, "OK, '${fileName}' did not -yet- exist")
			}
		}

		def download = {
			def fileName = params.get('file')
			def name = params.get('uploadr')
			def info	= session.getAttribute('uploadr')
			def savePath	= (name && info && info.get(name) && info.get(name).path) ? info.get(name).path : '/tmp'
			def file	= new File(savePath, fileName)
		
			if (file.exists()) {
				response.setStatus(200)
				response.setHeader("Content-disposition", "attachment; filename=\"${fileName}\"")
				response.setContentType("application/octet-stream")
				response.setContentLength(file.size() as int)
				
				// define input and output streams
				InputStream inStream = null
				OutputStream outStream = null
				
				// handle file upload
				try {
					inStream = new FileInputStream(file)
					outStream = response.getOutputStream()
					
					while (true) {
						synchronized (buffer) {
							int amountRead = inStream.read(buffer);
							if (amountRead == -1) {
								break
							}
							outStream.write(buffer, 0, amountRead)
						}
						outStream.flush()
					}
				} catch (Exception e) {
					// whoops, looks like something went wrong
					println "download failed! ${e.getMessage()}"
				} finally {
					if (inStream != null) inStream.close()
					if (outStream != null) outStream.close()
				}
			}
				
			// return false as we do not have a view
			return false
		}
		
	def parsefile = {

		log.error("==parseFile==")
		
//		def fileName = params.get('file')
//		def name 	 = params.get('uploadr')
//		def info	 = session.getAttribute('uploadr')
//		def savePath = (name && info && info.get(name) && info.get(name).path) ? info.get(name).path : '/tmp'
		def file	 = new File("C:/TEMP", "usbdatalogger.csv")
	
		if (file.exists()) {
//			response.setStatus(200)
//			response.setHeader("Content-disposition", "attachment; filename=\"${fileName}\"")
//			response.setContentType("application/octet-stream")
//			response.setContentLength(file.size() as int)

			Serie serie = Serie.get(1)
			
			//CSV IMPORT PLUGIN
			file.toCsvReader(['skipLines':'1']).eachLine { tokens ->
				Measure measure = new Measure( measureDate: new Date().parse("yyyy-M-d HH:mm", tokens[0]),        //Timestamp
												celsiusTemperature: Float.parseFloat(tokens[1]),		  //Temperature
												humidity : Integer.parseInt(tokens[2]))		 //Humidite

				measure.setSerie(serie)
				measure.save() 
			}
			
//			new Measure( measureDate: new Date().parse("yyyy-M-d HH:mm", tokens[0]),        //Timestamp
//				celsiusTemperature: Float.parseFloat(tokens[1]),		  //Temperature
//				humidity : Integer.parseInt(tokens[2]))		 //Humidite
//			   .setSerie(serie)
//			   .save()

			//BULK INSERT FOR PERFORMANCE
//			StatelessSession session = sessionFactory.openStatelessSession()
//			Transaction tx = session.beginTransaction()
//			file.eachLine() {line ->
//				Measure measure = new Measure( 
//										DateFormat.parse("yyyy-M-d HH:mm", line[0]),        //Timestamp
//										line[1],		  //Temperature
//										line[2])
//				measure.addToSerie(serie)
//				measure.save()
//				session.insert(measure)
//			}
//			tx.commit()
//			session.close()
		}
		
		render Serie.get(1) as JSON
		
	}
}
