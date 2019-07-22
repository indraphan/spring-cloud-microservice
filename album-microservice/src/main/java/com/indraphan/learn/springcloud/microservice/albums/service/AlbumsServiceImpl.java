package com.indraphan.learn.springcloud.microservice.albums.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.indraphan.learn.springcloud.microservice.albums.data.AlbumEntity;

@Service
public class AlbumsServiceImpl implements AlbumsService {

	@Override
	public List<AlbumEntity> getAlbums(String userId) {
		List<AlbumEntity> returnValue = new ArrayList<>();
		
		AlbumEntity albumEntity = new AlbumEntity();
		albumEntity.setUserId(userId);
		albumEntity.setAlbumId("album1Id");
		albumEntity.setDescription("album1 Description");
		albumEntity.setId(1L);
		albumEntity.setName("album 1 name");
		
		AlbumEntity albumEntity2 = new AlbumEntity();
		albumEntity.setUserId(userId);
		albumEntity.setAlbumId("album2Id");
		albumEntity.setDescription("album2 Description");
		albumEntity.setId(1L);
		albumEntity.setName("album 2 name");
		
		returnValue.add(albumEntity);
		returnValue.add(albumEntity2);
		
		return returnValue;
	}

}
