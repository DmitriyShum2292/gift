package config;

import org.springframework.jdbc.core.JdbcTemplate;

public class InitDB {
    public void initGift(JdbcConfig jdbcConfig){
        JdbcTemplate jdbcTemplate = jdbcConfig.getJdbcTemplate();
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS gift_certificate (" +
                "id serial constraint gift_certificate_pk primary key," +
                "name varchar ," +
                "description varchar, " +
                "price integer," +
                "duration integer," +
                "create_date timestamp," +
                "last_update_date timestamp," +
                "tags integer" +
                ")");
    }

    public void initTag(JdbcConfig jdbcConfig){
        JdbcTemplate jdbcTemplate = jdbcConfig.getJdbcTemplate();
        jdbcTemplate.update("CREATE TABLE tag(id serial constraint tag_pk primary key ,name varchar )"
        );
    }

    public void initGiftTag(JdbcConfig jdbcConfig){
        JdbcTemplate jdbcTemplate = jdbcConfig.getJdbcTemplate();
        jdbcTemplate.update("CREATE TABLE gift_tag(gift_id integer,tag_id integer)"
        );
    }

}
