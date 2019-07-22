package com.indraphan.learn.springcloud.microservice.albums.service;

import java.util.List;

import com.indraphan.learn.springcloud.microservice.albums.data.AlbumEntity;

public interface AlbumsService {
	List<AlbumEntity> getAlbums(String userId);
}
