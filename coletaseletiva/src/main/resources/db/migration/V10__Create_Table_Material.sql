CREATE TABLE IF NOT EXISTS material (
	ID_MATERIAL INT NOT NULL AUTO_INCREMENT,
	DS_MATERIAL VARCHAR(100) NOT NULL,
	VL_PESO DECIMAL(18,2) NOT NULL,
	ID_TIPO_MATERIAL INT NOT NULL,
	PRIMARY KEY (ID_MATERIAL, ID_TIPO_MATERIAL),
	CONSTRAINT fk_MATERIAL_TIPO_MATERIAL1 FOREIGN KEY (ID_TIPO_MATERIAL) REFERENCES tipo_material (ID_TIPO_MATERIAL)
);
