package com.serratec.techbank1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.serratec.techbank1.model.Conta;


@Component
@ConfigurationProperties("about")
public class AboutUsConfig {
	
	private Map<String,String> aboutUs;
	private List<String> colabs;
	private String howToUse;
	private Conta conta;
	
	
	
	public String getHowToUse() {
		return howToUse;
	}
	public void setHowToUse(String howToUse) {
		this.howToUse = howToUse;
	}
	public Map<String, String> getAboutUs() {
		return aboutUs;
	}
	public void setAboutUs(Map<String, String> aboutUs) {
		this.aboutUs = aboutUs;
	}
	public List<String> getColabs() {
		return colabs;
	}
	public void setColabs(List<String> colabs) {
		this.colabs = colabs;
	}

	
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	

}
