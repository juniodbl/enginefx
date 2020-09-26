package br.com.engine.resources;

import java.util.List;

public class Configurations {
	private List<ScenesDefinition> scenes;
	private Boolean debugMode;
	private Integer sizeW;
	private Integer sizeH;

	public List<ScenesDefinition> getScenes() {
		return scenes;
	}

	public void setScenes(List<ScenesDefinition> scenes) {
		this.scenes = scenes;
	}

	public Boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(Boolean debugMode) {
		this.debugMode = debugMode;
	}

	public Integer getSizeW() {
		return sizeW;
	}

	public void setSizeW(Integer sizeW) {
		this.sizeW = sizeW;
	}

	public Integer getSizeH() {
		return sizeH;
	}

	public void setSizeH(Integer sizeH) {
		this.sizeH = sizeH;
	}
}