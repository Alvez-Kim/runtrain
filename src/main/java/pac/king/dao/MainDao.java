package pac.king.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import pac.king.pojo.Menu;
import pac.king.pojo.User;

public interface MainDao {
	@Select("select * from USER")
	List<User> testQuery();
	
	@Select("SELECT count(0) FROM USER WHERE NAME = #{user.name} AND PASSWORD=#{user.password}")
	int checkUserExists(@Param("user")User user);
	
	@Select("SELECT * FROM USER WHERE NAME = #{user.name} AND PASSWORD=#{user.password}")
	User queryUserInfo(@Param("user")User user);
	
	@Select("SELECT * FROM MENU")
	List<Menu> queryMenu();
	
	@Insert("INSERT INTO USER VALUES(UUID(),#{user.name},#{user.password})")
	int insertUserInfo(@Param("user")User user);
}

