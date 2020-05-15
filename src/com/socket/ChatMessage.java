package com.socket;

public class ChatMessage {
//	private String type;
	private String sender;
	private String receiver;
	private String senderName = "";
	private String message;
	private Integer isPic;
	public ChatMessage(String sender, String receiver, String senderName, String message, Integer isPic) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.senderName = senderName;
		this.message = message;
		this.isPic = isPic;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getIsPic() {
		return isPic;
	}
	public void setIsPic(Integer isPic) {
		this.isPic = isPic;
	}
	
	@Override
	public String toString() {
		return "ChatMessage [sender=" + sender + ", receiver=" + receiver + ", senderName=" + senderName + ", message="
				+ message + ", isPic=" + isPic + "]";
	}

	
}
