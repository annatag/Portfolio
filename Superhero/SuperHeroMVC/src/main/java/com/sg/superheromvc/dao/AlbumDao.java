/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.dao;

import com.sg.superheromvc.model.Picture;
import java.util.List;

/**
 *
 * @author Anna
 */
public interface AlbumDao {
   public Picture addPicture(Picture picture);
    
    public void deletePicture(int pictureId);
    
    public Picture getPictureById(int pictureId);
    
    public List<Picture> getAllPictures(); 
}
