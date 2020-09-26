package br.com.engine.resources;

public enum TiposPropertiesManager
{
	DEBUG_MODE("debugMode"),
	SIZE_W("sizeW"),
	SIZE_H("sizeH");
	
	String valor = "";
	
	TiposPropertiesManager( String s )
	{
		valor = s;
	}
	
	public String getValor( )
	{
		return valor;
	}
}