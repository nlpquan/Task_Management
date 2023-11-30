package com.da.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.da.data.entity.UserEntity;

public class UserRowMapper implements RowMapper<UserEntity> {

	@Override
	public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new UserEntity(rs.getLong("id"),
				rs.getString("username"),
				rs.getString("password"),
				rs.getString("first_name"), 
				rs.getString("last_name"));
	}

}
