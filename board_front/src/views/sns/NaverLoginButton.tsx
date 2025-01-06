import React, { useEffect } from 'react';

const NaverLoginButton = () => {
  useEffect(() => {
    const naverScript = document.createElement('script');
    naverScript.src = 'https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js';
    naverScript.type = 'text/javascript';
    document.head.appendChild(naverScript);

    naverScript.onload = () => {
      const naverLogin = new (window as any).naver.LoginWithNaverId({
        clientId: 'yjfMOQYyNjEUXWmx2_7n',
        callbackUrl: 'http://localhost:3000/naver/callback',
        isPopup: false,
        loginButton: { color: 'green', type: 3, height: 50 },
      });
      naverLogin.init();
    };
  }, []);

  return <div id="naverIdLogin" />;
};

export default NaverLoginButton;
