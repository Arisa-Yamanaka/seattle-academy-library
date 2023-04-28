package jp.co.seattle.library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import jp.co.seattle.library.dto.UserInfo;
import jp.co.seattle.library.rowMapper.UserCountRowMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
//APIの入り口 APIとは、他のソフトウェアが外部から自分のソフトウェアへアクセスし利用できるようにしたもの
//ソフトウェアコンポーネントが互いにやりとりするのに使用するインタフェースの仕様
public class UsersService {
	final static Logger logger = LoggerFactory.getLogger(UsersService.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * ユーザー情報を登録する
	 * 
	 * @param userInfo ユーザー情報
	 */
	public void registUser(UserInfo userInfo) {

		// SQL生成
		String sql = "INSERT INTO users (email, password,reg_date,upd_date) VALUES ('" + userInfo.getEmail() + "','"
				+ userInfo.getPassword() + "',now(),now()" + ")";

		jdbcTemplate.update(sql);
	}

	/**
	 * ユーザー情報取得
	 * 
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @return ユーザー情報
	 */
	public String selectUserInfo(String email, String password) {
		try {
			String sql = "SELECT email, password FROM users WHERE email = '" + email + "' AND password = '" + password
					+ "'";
			UserInfo selectedUserInfo = jdbcTemplate.queryForObject(sql, new UserCountRowMapper());
			return selectedUserInfo;
		} catch (Exception e) {
			return "redirect:/null";
		}
	}

}


		if (password.length() >= 8 && password.matches("[0-9a-zA-Z]+")) {
			if (password.equals(passwordForCheck)) {
				
				UserInfo userInfo = new UserInfo();
				userInfo.setEmail(email);
				userInfo.setPassword(password);
				usersService.registUser(userInfo);
				return "redirect:/login";
			}else{model.addAttribute("errorMessage","パスワードが一致しません。");return"createAccount";}}else{model.addAttribute("errorMessage","パスワードは8文字以上かつ半角英数字に設定してください");

return"redirect/passwordreset";}}}