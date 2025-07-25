package com.aluracursos.screenmatch.service;

public interface IDataConverter {

    <T> T getData(String json, Class<T> clase);
}
