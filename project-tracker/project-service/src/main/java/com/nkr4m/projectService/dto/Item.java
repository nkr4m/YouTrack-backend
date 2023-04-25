package com.nkr4m.projectService.dto;

import java.util.List;

import com.nkr4m.projectService.document.Ticket;

public class Item {
	private String title;
	private List<Ticket> items;
	
	public Item() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Ticket> getItems() {
		return items;
	}

	public void setItems(List<Ticket> items) {
		this.items = items;
	}
	
	
}
