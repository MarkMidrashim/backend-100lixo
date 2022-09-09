package br.com.fiap.coletaseletiva.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public static <O, D> D parseObject(O origin, Class<D> destiny) {
		return mapper.map(origin, destiny);
	}

	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destiny) {
		List<D> destinyObjects = new ArrayList<D>();
		for (Object object : origin) {
			destinyObjects.add(mapper.map(object, destiny));
		}
		return destinyObjects;
	}
}
