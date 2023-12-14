package hello.login.web.session;

import static org.junit.jupiter.api.Assertions.*;

import hello.login.domain.member.Member;
import javax.servlet.http.HttpServletResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @DisplayName("")
    @Test
    void sessionTest(){
        MockHttpServletResponse response = new MockHttpServletResponse();

        Member member = new Member();
        sessionManager.createSession(member, response);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        Object result = sessionManager.getSession(request);
        Assertions.assertThat(result).isEqualTo(member);

        sessionManager.expire(request);
    }
}