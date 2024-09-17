package com.example.loggeospring.domain.servicios_api.cabaService.searchGeo.entities;


import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.util.AffineTransformation;

import java.util.Set;

public class GeoTransform {
  // Método para transformar de EPSG:4326 a EPSG:3857 (Web Mercator)
  public Point transformToEPSG3857(GeoLocation geoLocation) {
    double longitude = geoLocation.getLng();
    double latitude = geoLocation.getLat();

    double y = longitude * 20037508.34 / 180;
    double x = Math.log(Math.tan((90 + latitude) * Math.PI / 360)) * 20037508.34 / Math.PI;

    GeometryFactory geometryFactory = new GeometryFactory();
    Coordinate coordinate = new Coordinate(x, y);
    return geometryFactory.createPoint(coordinate);
  }
}
