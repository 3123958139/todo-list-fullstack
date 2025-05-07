<script setup>
import { ref } from 'vue'
import serverApi from '../services/server-api';

const activeForm = ref('login')

const toggleForm = (formName) => {
  activeForm.value = formName
}

const handleFocus = (e) => {
  e.target.parentElement.classList.add('addcolor')
}

const handleBlur = (e) => {
  e.target.parentElement.classList.remove('addcolor')
}
</script>

<template>
  <div id="particles-js" class="main-form-box">
    <div class="md-form">
      <div class="container">
        <div class="row">
          <div class="col-md-6 offset-md-3">
            <div class="panel panel-login">
              <div class="logo-top">
                <a href="#"><img src="/src/template/login/images/logo.png" alt="" /></a>
              </div>
              <div class="panel-heading">
                <div class="row">
                  <div class="col-lg-6 col-sm-6 col-xl-6">
                    <a href="#" 
                       :class="{ active: activeForm === 'login' }" 
                       @click.prevent="toggleForm('login')">
                      Login
                    </a>
                  </div>
                  <div class="col-lg-6 col-sm-6 col-xl-6">
                    <a href="#" 
                       :class="{ active: activeForm === 'register' }" 
                       @click.prevent="toggleForm('register')">
                      Register
                    </a>
                  </div>
                  <div class="or">OR</div>
                </div>
              </div>
              <div class="panel-body">
                <div class="row">
                  <div class="col-lg-12">
                    <form id="login-form" v-show="activeForm === 'login'">
                      <div class="form-group">
                        <label class="icon-lp"><i class="fas fa-user-tie"></i></label>
                        <input type="text" 
                               name="username" 
                               @focus="handleFocus" 
                               @blur="handleBlur" 
                               class="form-control" 
                               placeholder="Username" 
                               required>
                      </div>
                      <div class="form-group">
                        <label class="icon-lp"><i class="fas fa-key"></i></label>
                        <input type="password" 
                               name="password" 
                               @focus="handleFocus" 
                               @blur="handleBlur" 
                               class="form-control" 
                               placeholder="Password" 
                               required>
                      </div>
                      <div class="che-box">
                        <label class="checkbox-in">
                          <input name="checkbox" type="checkbox"> <span></span>
                          Remember Me
                        </label>
                      </div>
                      <ul>
                        <li>
                          <button class="fb"><i class="fab fa-facebook-f"></i> Connect with Facebook</button>
                        </li>
                        <li>
                          <button class="tw"><i class="fab fa-twitter"></i> Connect with Twitter</button>
                        </li>
                      </ul>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-sm-6 offset-sm-3">
                            <input type="submit" class="form-control btn btn-login" value="Log In">
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-lg-12">
                            <div class="text-center">
                              <a href="#" class="forgot-password">Forgot Password?</a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </form>

                    <form id="register-form" v-show="activeForm === 'register'">
                      <div class="form-group">
                        <label class="icon-lp"><i class="fas fa-user-tie"></i></label>
                        <input type="text" 
                               name="username" 
                               @focus="handleFocus" 
                               @blur="handleBlur" 
                               class="form-control" 
                               placeholder="Username" 
                               required>
                      </div>
                      <div class="form-group">
                        <label class="icon-lp"><i class="fas fa-envelope"></i></label>
                        <input type="email" 
                               name="email" 
                               @focus="handleFocus" 
                               @blur="handleBlur" 
                               class="form-control" 
                               placeholder="Email Address" 
                               required>
                      </div>
                      <div class="form-group">
                        <label class="icon-lp"><i class="fas fa-key"></i></label>
                        <input type="password" 
                               name="password" 
                               @focus="handleFocus" 
                               @blur="handleBlur" 
                               class="form-control" 
                               placeholder="Password" 
                               required>
                      </div>
                      <div class="form-group">
                        <label class="icon-lp"><i class="fas fa-key"></i></label>
                        <input type="password" 
                               name="confirm-password" 
                               @focus="handleFocus" 
                               @blur="handleBlur" 
                               class="form-control" 
                               placeholder="Confirm Password" 
                               required>
                      </div>
                      <ul>
                        <li>
                          <button class="fb"><i class="fab fa-facebook-f"></i> Connect with Facebook</button>
                        </li>
                        <li>
                          <button class="tw"><i class="fab fa-twitter"></i> Connect with Twitter</button>
                        </li>
                      </ul>
                      
                      <div class="che-box">
                        <label class="checkbox-in"> 
                          <input name="checkbox" type="checkbox"> <span></span>I agree to the <a href="#"> Terms and Conditions </a> and <a href="#">Privacy Policy </a>
                        </label>
                      </div>
                      
                      <div class="form-group">
                        <div class="row">
                          <div class="col-sm-6 offset-sm-3">
                            <input type="submit" class="form-control btn btn-register" value="Register Now">
                          </div>
                        </div>
                      </div>                                      
                    </form>
                  </div>
                </div>
              </div>
            </div>
            
            <p class="footer-company-name">All Rights Reserved. &copy; 2018 <a href="#">Login Register</a> <a target="_blank" href="http://www.mobanwang.com/" title="网页模板">网页模板</a></p>
            
          </div>
        </div>
      </div>  
    </div>
  </div>
</template>

<style scoped>
@import url('/src/template/login/css/bootstrap.min.css');
@import url('/src/template/login/css/style.css');
@import url('/src/template/login/css/all.min.css');
@import url('/src/template/login/css/responsive.css');
@import url('/src/template/login/css/custom.css');

#login-form, #register-form {
  transition: opacity 0.1s ease;
}
</style>