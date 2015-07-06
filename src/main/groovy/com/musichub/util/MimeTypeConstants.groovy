package com.musichub.util

final class MimeTypeConstants {

	protected static String s_strDefaultMimeType = "application/octet-stream"

	public static final Map MIME_TYPES = [
		ai: "application/postscript",
		aif: "audio/x-aiff",
		aifc: "audio/x-aiff",
		aiff: "audio/x-aiff",
	    asc: "text/plain",
	    asf: "video/x.ms.asf",
	    asx: "video/x.ms.asx",
	    au: "audio/basic",
	    avi: "video/x-msvideo",
	    bcpio: "application/x-bcpio",
	    bin: "application/octet-stream",
	    cab: "application/x-cabinet",
	    cdf: "application/x-netcdf",
	    "class": "application/java-vm",
	    cpio: "application/x-cpio",
	    cpt: "application/mac-compactpro",
	    crt: "application/x-x509-ca-cert",
	    csh: "application/x-csh",
	    css: "text/css",
	    csv: "text/comma-separated-values",
	    dcr: "application/x-director",
	    dir: "application/x-director",
	    dll: "application/x-msdownload",
	    dms: "application/octet-stream",
	    doc: "application/msword",
	    dtd: "application/xml-dtd",
		dvi: "application/x-dvi",
		dxr: "application/x-director",
		eps: "application/postscript",
		etx: "text/x-setext",
		exe: "application/octet-stream",
		ez: "application/andrew-inset",
		gif: "image/gif",
		gtar: "application/x-gtar",
		gz: "application/gzip",
		gzip: "application/gzip",
		hdf: "application/x-hdf",
		htc: "text/x-component",
		hqx: "application/mac-binhex40",
		html: "tex,html",
		htm: "text/html",
		ice: "x-conference/x-cooltalk",
		ief: "image/ief",
		iges: "model/iges",
		igs: "model/iges",
		jar: "application/java-archive",
		java: "text/plain",
		jnlp: "application/x-java-jnlp-file",
		jpeg: "image/jpeg",
		jpe: "image/jpeg",
		jpg: "image/jpeg",
		js: "application/x-javascript",
		jsp: "text/plain",
		kar: "audio/midi",
		latex: "application/x-latex",
		lha: "application/octet-stream",
		lzh: "application/octet-stream",
		man: "application/x-troff-man",
		mathml: "application/mathml+xml",
		me: "application/x-troff-me",
		mesh: "model/mesh",
		mid: "audio/midi",
		midi: "audio/midi",
		mif: "application/vnd.mif",
		mol: "chemical/x-mdl-molfile",
		movie: "video/x-sgi-movie",
		mov: "video/quicktime",
		mp2: "audio/mpeg",
		mp3: "audio/mpeg",
		mpeg: "video/mpeg",
		mpe: "video/mpeg",
		mpga: "audio/mpeg",
		mpg: "video/mpeg",
		ms: "application/x-troff-ms",
		msh: "model/mesh",
		msi: "application/octet-stream",
		nc: "application/x-netcdf",
		oda: "application/oda",
		ogg: "application/ogg",
		pbm: "image/x-portable-bitmap",
		pdb: "chemical/x-pdb",
		pdf: "application/pdf",
		pgm: "image/x-portable-graymap",
		pgn: "application/x-chess-pgn",
		png: "image/png",
		pnm: "image/x-portable-anymap",
		ppm: "image/x-portable-pixmap",
		ppt: "application/vnd.ms-powerpoint",
		ps: "application/postscript",
		qt: "video/quicktime",
		ra: "audio/x-pn-realaudio",
		ra: "audio/x-realaudio",
		ram: "audio/x-pn-realaudio",
		ras: "image/x-cmu-raster",
		rdf: "application/rdf+xml",
		rgb: "image/x-rgb",
		rm: "audio/x-pn-realaudio",
		roff: "application/x-troff",
		rpm: "application/x-rpm",
		rpm: "audio/x-pn-realaudio",
		rtf: "application/rtf",
		rtx: "text/richtext",
		ser: "application/java-serialized-object",
		sgml: "text/sgml",
		sgm: "text/sgml",
		sh: "application/x-sh",
		shar: "application/x-shar",
		silo: "model/mesh",
		sit: "application/x-stuffit",
		skd: "application/x-koan",
		skm: "application/x-koan",
		skp: "application/x-koan",
		skt: "application/x-koan",
		smi: "application/smil",
		smil: "application/smil",
		snd: "audio/basic",
		spl: "application/x-futuresplash",
		src: "application/x-wais-source",
		sv4cpio: "application/x-sv4cpio",
		sv4crc: "application/x-sv4crc",
		svg: "image/svg+xml",
		swf: "application/x-shockwave-flash",
		t: "application/x-troff",
		tar: "application/x-tar",
		"tar.gz": "application/x-gtar",
		tcl: "application/x-tcl",
		tex: "application/x-tex",
		texi: "application/x-texinfo",
		texinfo: "application/x-texinfo",
		tgz: "application/x-gtar",
		tiff: "image/tiff",
		tif: "image/tiff",
		tr: "application/x-troff",
		tsv: "text/tab-separated-values",
		txt: "text/plain",
		ustar: "application/x-ustar",
		vcd: "application/x-cdlink",
		vrml: "model/vrml",
		vxml: "application/voicexml+xml",
		wav: "audio/x-wav",
		wbmp: "image/vnd.wap.wbmp",
		wmlc: "application/vnd.wap.wmlc",
		wmlsc: "application/vnd.wap.wmlscriptc",
		wmls: "text/vnd.wap.wmlscript",
		wml: "text/vnd.wap.wml",
		wrl: "model/vrml",
		"wtls-ca-certificate": "application/vnd.wap.wtls-ca-certificate",
		xbm: "image/x-xbitmap",
		xht: "application/xhtml+xml",
		xhtml: "application/xhtml+xml",
		xls: "application/vnd.ms-excel",
		xml: "application/xml",
		xpm: "image/x-xpixmap",
		xpm: "image/x-xpixmap",
		xsl: "application/xml",
		xslt: "application/xslt+xml",
		xul: "application/vnd.mozilla.xul+xml",
		xwd: "image/x-xwindowdump",
		xyz: "chemical/x-xyz",
		z: "application/compress",
		zip: "application/zip"
	]

	private MimeTypeConstants() {
	   // Do nothing
	}
	
	/**
	 * Method getting particular Mime type for the extension (key)
	 *
	 * @param strKey - key value for returning Mime type
	 * @return String
	 */
	public static String getMimeType(String strKey) {

	   String strMimeType = MimeTypeConstants.MIME_TYPES.get(strKey)

	   if (!strMimeType) {
		   strMimeType = s_strDefaultMimeType;
	   }

	   return strMimeType;
	}
}
