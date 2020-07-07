package com.fms.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email.properties")
public class EmailProperties {

	private String mailSmtpAuth;
	private String mailSmtpAuthStatus;
	private String mailSmtpStarttlsEnable;
	private String mailSmtpStarttlsEnableStatus;
	private String mailSmtpHost;
	private String mailSmtpHostName;
	private String mailSmtpPort;
	private String mailSmtpPortNumber;
	private String emailAddress;
	private String emailPassword;
	private String nodeUrl;

	public String getMailSmtpAuth() {
		return mailSmtpAuth;
	}

	public void setMailSmtpAuth(String mailSmtpAuth) {
		this.mailSmtpAuth = mailSmtpAuth;
	}

	public String getMailSmtpAuthStatus() {
		return mailSmtpAuthStatus;
	}

	public void setMailSmtpAuthStatus(String mailSmtpAuthStatus) {
		this.mailSmtpAuthStatus = mailSmtpAuthStatus;
	}

	public String getMailSmtpStarttlsEnable() {
		return mailSmtpStarttlsEnable;
	}

	public void setMailSmtpStarttlsEnable(String mailSmtpStarttlsEnable) {
		this.mailSmtpStarttlsEnable = mailSmtpStarttlsEnable;
	}

	public String getMailSmtpStarttlsEnableStatus() {
		return mailSmtpStarttlsEnableStatus;
	}

	public void setMailSmtpStarttlsEnableStatus(String mailSmtpStarttlsEnableStatus) {
		this.mailSmtpStarttlsEnableStatus = mailSmtpStarttlsEnableStatus;
	}

	public String getMailSmtpHost() {
		return mailSmtpHost;
	}

	public void setMailSmtpHost(String mailSmtpHost) {
		this.mailSmtpHost = mailSmtpHost;
	}

	public String getMailSmtpHostName() {
		return mailSmtpHostName;
	}

	public void setMailSmtpHostName(String mailSmtpHostName) {
		this.mailSmtpHostName = mailSmtpHostName;
	}

	public String getMailSmtpPort() {
		return mailSmtpPort;
	}

	public void setMailSmtpPort(String mailSmtpPort) {
		this.mailSmtpPort = mailSmtpPort;
	}

	public String getMailSmtpPortNumber() {
		return mailSmtpPortNumber;
	}

	public void setMailSmtpPortNumber(String mailSmtpPortNumber) {
		this.mailSmtpPortNumber = mailSmtpPortNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getNodeUrl() {
		return nodeUrl;
	}

	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}

}
