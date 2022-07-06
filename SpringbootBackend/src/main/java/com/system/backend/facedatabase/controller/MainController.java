package com.system.backend.facedatabase.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.system.backend.facedatabase.entity.Face2classes;
import com.system.backend.facedatabase.entity.Face4classes;
import com.system.backend.facedatabase.entity.Face8attributes;
import com.system.backend.facedatabase.entity.Face15attributes;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.system.backend.facedatabase.entity.SimpleResultBean;
import com.system.backend.facedatabase.entity.ResultBean;

import com.system.backend.facedatabase.service.IFace2classesService;
import com.system.backend.facedatabase.service.IFace4classesService;
import com.system.backend.facedatabase.service.IFace8attributesService;
import com.system.backend.facedatabase.service.IFace15attributesService;
import java.io.*;
import java.util.List;
import javax.annotation.Resource;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-05-16
 */
@RestController
public class MainController {
	@Value("${databaseDirectory}")
	private String databaseDirectory;
	@Value("${flaskBackend}")
	private String flaskBackend;
	
	@Resource
	private IFace2classesService face2classesService;
	@Resource
	private IFace4classesService face4classesService;
	@Resource
	private IFace8attributesService face8attributesService;
	@Resource
	private IFace15attributesService face15attributesService;
	
	@RequestMapping("/update_database")
	public SimpleResultBean update_database() {
		SimpleResultBean result = new SimpleResultBean();
		File f2 = new File(databaseDirectory+"face_2/");
        File[] files2 = f2.listFiles();
        for (int i = 0; i < files2.length; i++) {
        	String filename = files2[i].getName();
        	Integer imageClass = 0;
        	if(filename.split("_")[1].split("\\.")[0].equals("01")) 
        		imageClass = 1;
        	Face2classes line = Face2classes.builder().imageFilename(files2[i].getName())
        			.imageClass(imageClass).build();
        	if(!face2classesService.save(line)) {
    			result.setCode(1);
    			result.setMsg("update facedatabase failed");
    			return result;
    		}
        }
        
        File f4 = new File(databaseDirectory+"face_4/");
        File[] files4 = f4.listFiles();
        for (int i = 0; i < files4.length; i++) {
        	String filename = files4[i].getName();
        	Integer imageClass = 0;
        	if(filename.split("_")[1].split("\\.")[0].equals("0100")) 
        		imageClass = 1;
        	else if(filename.split("_")[1].split("\\.")[0].equals("0010")) 
        		imageClass = 2;
        	else if(filename.split("_")[1].split("\\.")[0].equals("0001")) 
        		imageClass = 3;
        	Face4classes line = Face4classes.builder().imageFilename(files4[i].getName())
        			.imageClass(imageClass).build();
        	if(!face4classesService.save(line)) {
    			result.setCode(1);
    			result.setMsg("update facedatabase failed");
    			return result;
    		}
        }
        
        File f8 = new File(databaseDirectory+"face_8/");
        File[] files8 = f8.listFiles();
        for (int i = 0; i < files8.length; i++) {
        	String filename = files8[i].getName();
        	String embed = filename.split("_")[1].split("\\.")[0];
        	Face8attributes line = Face8attributes.builder().imageFilename(files8[i].getName())
        			.bigLips(Integer.parseInt(embed.substring(0,1)))
        			.bigNose(Integer.parseInt(embed.substring(1,2)))
        			.eyeglasses(Integer.parseInt(embed.substring(2,3)))
        			.male(Integer.parseInt(embed.substring(3,4)))
        			.noBeard(Integer.parseInt(embed.substring(4,5)))
        			.smiling(Integer.parseInt(embed.substring(5,6)))
        			.wearingHat(Integer.parseInt(embed.substring(6,7)))
        			.young(Integer.parseInt(embed.substring(7)))
        			.build();
        	if(!face8attributesService.save(line)) {
    			result.setCode(1);
    			result.setMsg("update facedatabase failed");
    			return result;
    		}
        }
        
        File f15 = new File(databaseDirectory+"face_15/");
        File[] files15 = f15.listFiles();
        for (int i = 0; i < files15.length; i++) {
        	String filename = files15[i].getName();
        	String embed = filename.split("_")[1].split("\\.")[0];
        	Face15attributes line = Face15attributes.builder().imageFilename(files15[i].getName())
        			.beard(Integer.parseInt(embed.substring(0,1)))
        			.bald(Integer.parseInt(embed.substring(1,2)))
        			.eyeglasses(Integer.parseInt(embed.substring(2,3)))
        			.smiling(Integer.parseInt(embed.substring(3,4)))
        			.wearingHat(Integer.parseInt(embed.substring(4,5)))
        			.male(Integer.parseInt(embed.substring(5,6)))
        			.shadow(Integer.parseInt(embed.substring(6,7)))
        			.mustache(Integer.parseInt(embed.substring(7,8)))
        			.bangs(Integer.parseInt(embed.substring(8,9)))
        			.blackHair(Integer.parseInt(embed.substring(9,10)))
        			.blondeHair(Integer.parseInt(embed.substring(10,11)))
        			.brownHair(Integer.parseInt(embed.substring(11,12)))
        			.grayHair(Integer.parseInt(embed.substring(12,13)))
        			.straightHair(Integer.parseInt(embed.substring(13,14)))
        			.wavyHair(Integer.parseInt(embed.substring(14)))
        			.build();
        	if(!face15attributesService.save(line)) {
    			result.setCode(1);
    			result.setMsg("update facedatabase failed");
    			return result;
    		}
        }
        result.setCode(200);
		result.setMsg("success");
		return result;
	}
	
	@RequestMapping("/get_database_2")
	public ResultBean get_database_2(Integer imageClass) {
		ResultBean result = new ResultBean();
		QueryWrapper<Face2classes> wrapper = null;
		List<Face2classes> filename_list = null;
		if(imageClass!=-1) {
			wrapper=new QueryWrapper<>();
			wrapper.eq("imageClass", imageClass);
			filename_list = face2classesService.list(wrapper);
		}
		else {
			filename_list = face2classesService.list();
		}
		String[] data = new String[filename_list.size()];
		for(int i=0;i<filename_list.size();i++) {
			data[i] = filename_list.get(i).getImageFilename();
		}
		result.setCode(200);
		result.setMsg("success");
		result.setData(data);
		return result;
	}
	
	@RequestMapping("/get_database_4")
	public ResultBean get_database_4(Integer sex, Integer smiling) {
		ResultBean result = new ResultBean();
		QueryWrapper<Face4classes> wrapper = null;
		List<Face4classes> filename_list = null;
		if(sex==1 && smiling==0) {
			wrapper=new QueryWrapper<>();
			wrapper.eq("imageClass", 0);
			filename_list = face4classesService.list(wrapper);
		}
		else if(sex==1 && smiling==1) {
			wrapper=new QueryWrapper<>();
			wrapper.eq("imageClass", 1);
			filename_list = face4classesService.list(wrapper);
		}
		else if(sex==0 && smiling==0) {
			wrapper=new QueryWrapper<>();
			wrapper.eq("imageClass", 2);
			filename_list = face4classesService.list(wrapper);
		}
		else if(sex==0 && smiling==1) {
			wrapper=new QueryWrapper<>();
			wrapper.eq("imageClass", 3);
			filename_list = face4classesService.list(wrapper);
		}
		else if(sex==-1 && smiling==0) {
			wrapper=new QueryWrapper<>();
			wrapper.eq("imageClass", 0).or().eq("imageClass", 2);
			filename_list = face4classesService.list(wrapper);
		}
		else if(sex==-1 && smiling==1) {
			wrapper=new QueryWrapper<>();
			wrapper.eq("imageClass", 1).or().eq("imageClass", 3);
			filename_list = face4classesService.list(wrapper);
		}
		else if(sex==0 && smiling==-1) {
			wrapper=new QueryWrapper<>();
			wrapper.eq("imageClass", 2).or().eq("imageClass", 3);
			filename_list = face4classesService.list(wrapper);
		}
		else if(sex==1 && smiling==-1) {
			wrapper=new QueryWrapper<>();
			wrapper.eq("imageClass", 0).or().eq("imageClass", 1);
			filename_list = face4classesService.list(wrapper);
		}
		else {
			filename_list = face4classesService.list();
		}
		String[] data = new String[filename_list.size()];
		for(int i=0;i<filename_list.size();i++) {
			data[i] = filename_list.get(i).getImageFilename();
		}
		result.setCode(200);
		result.setMsg("success");
		result.setData(data);
		return result;
	}
	
	@RequestMapping("/get_database_8")
	public ResultBean get_database_8(Integer bigLips, Integer bigNose,
			Integer eyeglasses, Integer male, Integer noBeard,
			Integer smiling, Integer wearingHat, Integer young) {
		ResultBean result = new ResultBean();
		QueryWrapper<Face8attributes> wrapper = null;
		List<Face8attributes> filename_list = null;
		wrapper=new QueryWrapper<>();
		System.out.println(bigLips);
		wrapper.eq("bigLips", bigLips).eq("bigNose", bigNose).eq("eyeglasses", eyeglasses)
			.eq("male", male).eq("noBeard", noBeard).eq("smiling",smiling)
			.eq("wearingHat", wearingHat).eq("young", young);
			
		filename_list = face8attributesService.list(wrapper);
		String[] data = new String[filename_list.size()];
		for(int i=0;i<filename_list.size();i++) {
			data[i] = filename_list.get(i).getImageFilename();
		}
		result.setCode(200);
		result.setMsg("success");
		result.setData(data);
		return result;
	}
	
	@RequestMapping("/get_database_15")
	public ResultBean get_database_15(Integer beard, Integer bald,
			Integer eyeglasses, Integer smiling, Integer wearingHat,
			Integer male, Integer shadow, Integer mustache,
			Integer bangs, Integer blackHair, Integer blondeHair,
			Integer brownHair, Integer grayHair, Integer straightHair,
			Integer wavyHair) {
		ResultBean result = new ResultBean();
		QueryWrapper<Face15attributes> wrapper = null;
		List<Face15attributes> filename_list = null;
		wrapper=new QueryWrapper<>();
		wrapper.eq("beard", beard).eq("bald", bald).eq("eyeglasses", eyeglasses)
			.eq("smiling", smiling).eq("wearingHat", wearingHat).eq("male", male)
			.eq("shadow", shadow).eq("mustache", mustache).eq("bangs", bangs)
			.eq("blackHair", blackHair).eq("blondeHair", blondeHair)
			.eq("brownHair", brownHair).eq("grayHair", grayHair)
			.eq("straightHair", straightHair).eq("wavyHair", wavyHair);
		filename_list = face15attributesService.list(wrapper);
		String[] data = new String[filename_list.size()];
		for(int i=0;i<filename_list.size();i++) {
			data[i] = filename_list.get(i).getImageFilename();
		}
		result.setCode(200);
		result.setMsg("success");
		result.setData(data);
		return result;
	}
	
	@RequestMapping("/generate_gan2")
	public SimpleResultBean generate_gan2(Integer imageClass) {
		SimpleResultBean result = new SimpleResultBean();
		MultiValueMap<String,Object> dataToPython = new LinkedMultiValueMap<>();
		String embed_str;
		if(imageClass==-1) {
			Random random=new Random();
			Integer a = (Integer)random.nextInt(2);
			if(a==0) {
				embed_str = "0,1";
			}
			else {
				embed_str = "1,0";
			}
		}
		else if(imageClass==0) {
			embed_str = "1,0";
		}
		else {
			embed_str = "0,1";
		}
		dataToPython.add("embed_str", embed_str);
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://" + flaskBackend + "generate_gan2",dataToPython,String.class);
		String body = responseEntity.getBody();
		if(!body.equals("Done")) {
			result.setCode(1);
			result.setMsg("failed");
			return result;
		}
		result.setCode(200);
		result.setMsg("success");
		return result;
	}
	
	@RequestMapping("/generate_gan4")
//	sex 0 女性 sex 1男性 smiling 0非微笑 smiling 1微笑
	public SimpleResultBean generate_gan4(Integer sex, Integer smiling) {
		SimpleResultBean result = new SimpleResultBean();
		MultiValueMap<String,Object> dataToPython = new LinkedMultiValueMap<>();
		String embed_str;
//		embed_str 1,0,0,0 非微笑男性
//				  0,1,0,0 微笑男性
//		          0,0,1,0 非微笑女性
//				  0,0,0,1 微笑女性
		if(sex==1 && smiling==0) {
			embed_str = "1,0,0,0";
		}
		else if(sex==1 && smiling==1) {
			embed_str = "0,1,0,0";
		}
		else if(sex==0 && smiling==0) {
			embed_str = "0,0,1,0";
		}
		else if(sex==0 && smiling==1) {
			embed_str = "0,0,0,1";
		}
		else if(sex==-1 && smiling==0) {
			Random random=new Random();
			Integer a = (Integer)random.nextInt(2);
			if(a==0) {
				embed_str = "1,0,0,0";
			}
			else {
				embed_str = "0,0,1,0";
			}
		}
		else if(sex==-1 && smiling==1) {
			Random random=new Random();
			Integer a = (Integer)random.nextInt(2);
			if(a==0) {
				embed_str = "0,1,0,0";
			}
			else {
				embed_str = "0,0,0,1";
			}
		}
		else if(sex==0 && smiling==-1) {
			Random random=new Random();
			Integer a = (Integer)random.nextInt(2);
			if(a==0) {
				embed_str = "0,0,0,1";
			}
			else {
				embed_str = "0,0,1,0";
			}
		}
		else if(sex==1 && smiling==-1) {
			Random random=new Random();
			Integer a = (Integer)random.nextInt(2);
			if(a==0) {
				embed_str = "0,1,0,0";
			}
			else {
				embed_str = "1,0,0,0";
			}
		}
		else {
			Random random=new Random();
			Integer a = (Integer)random.nextInt(2);
			if(a==0) {
				embed_str = "1,0,0,0";
			}
			else if(a==1){
				embed_str = "0,1,0,0";
			}
			else if(a==2){
				embed_str = "0,0,1,0";
			}
			else {
				embed_str = "0,0,0,1";
			}
		}
		dataToPython.add("embed_str", embed_str);
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://" + flaskBackend + "generate_gan4",dataToPython,String.class);
		String body = responseEntity.getBody();
		if(!body.equals("Done")) {
			result.setCode(1);
			result.setMsg("failed");
			return result;
		}
		result.setCode(200);
		result.setMsg("success");
		return result;
	}
	
	@RequestMapping("/generate_gan8")
	public SimpleResultBean generate_gan8(Integer bigLips, Integer bigNose,
			Integer eyeglasses, Integer male, Integer noBeard,
			Integer smiling, Integer wearingHat, Integer young) {
		SimpleResultBean result = new SimpleResultBean();
		MultiValueMap<String,Object> dataToPython = new LinkedMultiValueMap<>();
		String embed_str = bigLips.toString()+","+bigNose.toString()+","+eyeglasses.toString()
			+","+male.toString()+","+noBeard.toString()+","+smiling.toString()+","
			+wearingHat.toString()+","+young.toString();
		dataToPython.add("embed_str", embed_str);
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://" + flaskBackend + "generate_gan8",dataToPython,String.class);
		String body = responseEntity.getBody();
		if(!body.equals("Done")) {
			result.setCode(1);
			result.setMsg("failed");
			return result;
		}
		result.setCode(200);
		result.setMsg("success");
		return result;
	}
	
	@RequestMapping("/generate_gan15")
	public SimpleResultBean generate_gan15(Integer beard, Integer bald,
			Integer eyeglasses, Integer smiling, Integer wearingHat,
			Integer male, Integer shadow, Integer mustache,
			Integer bangs, Integer blackHair, Integer blondeHair,
			Integer brownHair, Integer grayHair, Integer straightHair,
			Integer wavyHair) {
		SimpleResultBean result = new SimpleResultBean();
		MultiValueMap<String,Object> dataToPython = new LinkedMultiValueMap<>();
		String embed_str = beard.toString()+","+bald.toString()+","+eyeglasses.toString()
			+","+smiling.toString()+","+wearingHat.toString()+","+male.toString()+","
			+shadow.toString()+","+mustache.toString()+","+bangs.toString()+","
			+blackHair.toString()+","+blondeHair.toString()+","+brownHair.toString()
			+","+grayHair.toString()+","+straightHair.toString()+","+wavyHair.toString();
		dataToPython.add("embed_str", embed_str);
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://" + flaskBackend + "generate_gan15",dataToPython,String.class);
		String body = responseEntity.getBody();
		if(!body.equals("Done")) {
			result.setCode(1);
			result.setMsg("failed");
			return result;
		}
		result.setCode(200);
		result.setMsg("success");
		return result;
	}
	
}
