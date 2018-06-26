package com.recon.service;

import java.util.List;

import com.recon.model.Profile;

public interface ProfileService {
	public Profile getProfileByUsername(String username);

	public List<Profile> getAllProfiles();
}
