package org.seniors.rest.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataParam;

@Component
@Path("/upload")
public class UploadRequest extends GenericRequest implements Serializable {

	private static final long serialVersionUID = 8317074138773801694L;

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response upload(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("flowFilename") InputStream fileNameStream) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(fileNameStream));

			String realPath = this.servletContext.getRealPath("/");	
			if(realPath.indexOf("\\")!=-1){
				realPath = realPath.replaceAll("\\\\", "/");
			}
			String serverPath = realPath + "banners/";
			String filePath = serverPath + br.readLine().trim();

			saveFile(fileInputStream, filePath);
			return Response.status(200).entity("arquivo salvo com sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(404).entity("arquivo com problemas").build();
	}

	// save uploaded file to new location
	private void saveFile(InputStream uploadedInputStream, String serverLocation) {

		try {
			OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(serverLocation));

			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();

			uploadedInputStream.close();

			/**
			 * byte[] arquivo = IOUtils.toByteArray(uploadedInputStream);
			 * FileOutputStream output = new FileOutputStream(new
			 * File(serverLocation)); IOUtils.write(arquivo, output);
			 */

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}