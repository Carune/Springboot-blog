package com.cos.blog.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@Generated("jsonschema2pojo")
public class KakaoProfile {

	public Long id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	@JsonIgnoreProperties(ignoreUnknown=true)
	@Data
	@Generated("jsonschema2pojo")
	public class Properties {

		public String nickname;
		public String profile_Image;
		public String thumbnail_Image;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	@JsonIgnoreProperties(ignoreUnknown=true)
	@Data
	@Generated("jsonschema2pojo")
	public class KakaoAccount {

		public Boolean profile_nickname_needs_agreement;
		public Boolean profile_image_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}
		@JsonIgnoreProperties(ignoreUnknown=true)
		@Data
		@Generated("jsonschema2pojo")
		public class Profile {

			public String nickname;
			public String thumbnail_image_url;
			public String profile_image_url;
			public Boolean is_default_image;
			private Map<String, Object> additionalProperties = new HashMap<String, Object>();

			public Map<String, Object> getAdditionalProperties() {
				return this.additionalProperties;
			}

			public void setAdditionalProperty(String name, Object value) {
				this.additionalProperties.put(name, value);
			}

		}

	}

}
