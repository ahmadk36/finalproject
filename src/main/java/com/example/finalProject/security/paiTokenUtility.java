package com.example.finalProject.security;

import com.example.finalProject.pojo.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class paiTokenUtility {

  public final Integer expDate = 60000;
  public final String keySign = "Test";

  public String generateToken(String username) {
    Map<String, Object> test = new HashMap<String, Object>();
    test.put("username", username);

    return Jwts
      .builder()
      .setClaims(test)
      .signWith(SignatureAlgorithm.HS512, keySign)
      .compact();
  }

  public Result checkToken(String token) {
    Result result = new Result();

    Map<String, Object> resultMap = new HashMap<>();
    try {
      Claims s = Jwts
        .parser()
        .setSigningKey(keySign)
        .parseClaimsJws(token)
        .getBody();
      result.setStatus("Succ");
      String username = (String) s.get("username");
      resultMap.put("username", username);
      result.setResultMap(resultMap);
      return result;
    } catch (SignatureException ex) {
      result.setStatus("Failed");
      resultMap.put("error", "Invalid JWT signature");
      result.setResultMap(resultMap);
      return result;
    } catch (MalformedJwtException ex) {
      result.setStatus("Failed");
      resultMap.put("error", "Invalid JWT token");
      result.setResultMap(resultMap);
      return result;
    } catch (ExpiredJwtException ex) {
      result.setStatus("Failed");
      resultMap.put("error", "Expired JWT token");
      result.setResultMap(resultMap);
      return result;
    } catch (UnsupportedJwtException ex) {
      result.setStatus("Failed");
      resultMap.put("error", "Unsupported JWT token");
      result.setResultMap(resultMap);
      return result;
    } catch (IllegalArgumentException ex) {
      result.setStatus("Failed");
      resultMap.put("error", "JWT string is empty");
      result.setResultMap(resultMap);
      return result;
    } catch (Exception e) {
      result.setStatus("Failed");
      resultMap.put("error", e.getMessage());
      result.setResultMap(resultMap);
      return result;
    }
  }
}
