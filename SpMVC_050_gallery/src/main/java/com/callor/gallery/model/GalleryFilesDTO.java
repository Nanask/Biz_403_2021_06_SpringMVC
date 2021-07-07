package com.callor.gallery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GalleryFilesDTO {
	
	private Long g_seq; //	bigint
	private String g_writer; //	varchar(20)
	private String g_date; //	varchar(10)
	private String g_time; //	varchar(10)
	private String g_subject; //	varchar(50)
	private String g_content; //	varchar(1000)
	private String g_image; //	varchar(125)
	private String f_seq; //	bigint
	private String f_original; //	varchar(125)
	private String f_upname; //	varchar(125)

}
