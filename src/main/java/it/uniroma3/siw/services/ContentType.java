package it.uniroma3.siw.services;

public class ContentType {
	public static String contentTypeToExtension(String contentType) {
		switch(contentType) {
			case "image/png": return ".png";
			case "image/jpg": return ".jpg";
			case "image/jpeg": return ".jpeg";
			case "image/gif": return ".gif";
			default: return contentType;
		}
	}
}
