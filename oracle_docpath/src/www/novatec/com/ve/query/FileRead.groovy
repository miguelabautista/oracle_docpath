package www.novatec.com.ve.query

class FileRead {

	File file = new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 6.0\\conf\\db.conf_oracle")
	String url
	
	FileRead(){
		if(!file.exists()){
			File newFile = new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 6.0\\conf\\db.conf_oracle")
			newFile.write("jdbc:oracle:thin:docadmin/docadmin@(description=(address_list=(address=(protocol=tcp)(host=10.124.0.53)(port=1521))(address=(protocol=tcp)(host=10.124.0.54)(port=1521)))(source_route=yes)(connect_data=(service_name=SATDESA)))")
		}
	}

	def getUrl(){		
		file.eachLine{url = it}
		return url
	}
}
