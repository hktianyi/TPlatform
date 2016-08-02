<%@ page pageEncoding="UTF-8" language="java" %>
<!-- Sidebars -->
<!-- Right Sidebar -->
<ul id="slide-out" class="side-nav">
  <li class="sidenav-header">
    <!-- Srearch bar -->
    <nav class="transparent no-shadow">
      <div class="nav-wrapper">
        <form>
          <div class="input-field">
            <input id="search" type="search" required>
            <label for="search"><i class="ion-android-search"></i></label>
            <i class="ion-android-close"></i>
          </div>
        </form>
      </div>
    </nav>
  </li>
  <!-- Tabs -->
  <li>
    <ul class="tabs">
      <li class="tab col s3"><a class="active" href="login.html#sidebar1">Social</a></li>
      <li class="tab col s3"><a href="login.html#sidebar2">Chat</a></li>
    </ul>
  </li>
  <li id="sidebar1" class="p-20">
    <!-- Twitter -->
    <div class="twitter">
      <h6 class="follow-us"><i class="ion-social-twitter"></i> Follow us on Twitter</h6>
      <div class="tweet">
        <h3>@Codnauts</h3>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod <a href="login.html#">#tempor</a>.</p>
      </div>
      <div class="tweet">
        <h3>@Codnauts</h3>
        <p>Excepteur sint occaecat cupidatat non proident, sunt in <a href="login.html#">#voluptate</a> culpa qui officia deserunt mollit anim.</p>
      </div>
      <div class="tweet">
        <h3>@Codnauts</h3>
        <p>At vero eos et accusamus et iusto odio <a href="login.html#">#dignissimos</a> <a href="login.html#">#ducimus</a> qui blanditiis praesentium.</p>
      </div>
    </div>
    <!-- Facebook -->
    <div class="facebook">
      <h6 class="follow-us">Notifications</h6>
      <div class="face-notification">
        <img src="../static/img/user2.jpg" alt="" class="cricle">
        <div>
          <p>Mike Green</p>
          <span>Sent you a message</span>
          <span class="small">Today at 16:48</span>
        </div>
      </div>
      <div class="face-notification">
        <img src="../static/img/user.jpg" alt="" class="cricle">
        <div>
          <p>Lara Connors</p>
          <span>Post a photo with you</span>
          <span class="small">Today at 14:26</span>
        </div>
      </div>
      <div class="face-notification">
        <img src="../static/img/user3.jpg" alt="" class="cricle">
        <div>
          <p>Mike Green</p>
          <span>Post something...</span>
          <span class="small">Yesterday at 03:19</span>
        </div>
      </div>
    </div>

  </li>
  <li id="sidebar2" class="p-20">
    <!-- Chat -->
    <div class="chat-sidebar">
      <div class="chat-img">
        <img src="../static/img/user.jpg" alt="" class="cricle">
        <span class="dot green"></span>
      </div>
      <div class="chat-message">
        <p>Mike Green</p>
        <span>Sent you a message</span>
        <span class="small">online</span>
      </div>
    </div>

    <div class="chat-sidebar">
      <div class="chat-img">
        <img src="../static/img/user2.jpg" alt="" class="cricle">
        <span class="dot green"></span>
      </div>
      <div class="chat-message">
        <p>Lora Bell</p>
        <span>6 New messages</span>
        <span class="small">online</span>
      </div>
    </div>

    <div class="chat-sidebar">
      <div class="chat-img">
        <img src="../static/img/user3.jpg" alt="" class="cricle">
        <span class="dot orange"></span>
      </div>
      <div class="chat-message">
        <p>Tony Lee</p>
        <span>Away from keyboard.</span>
        <span class="small">Away</span>
      </div>
    </div>

    <div class="chat-sidebar">
      <div class="chat-img">
        <img src="../static/img/user4.jpg" alt="" class="cricle">
        <span class="dot grey"></span>
      </div>
      <div class="chat-message">
        <p>Jim Connor</p>
        <span>Is offline.</span>
        <span class="small">offline</span>
      </div>
    </div>

    <div class="chat-sidebar">
      <div class="chat-img">
        <img src="../static/img/user5.jpg" alt="" class="cricle">
        <span class="dot green"></span>
      </div>
      <div class="chat-message">
        <p>Sara Lower</p>
        <span>Sent you a message</span>
        <span class="small">online</span>
      </div>
    </div>

    <div class="chat-sidebar">
      <div class="chat-img">
        <img src="../static/img/user.jpg" alt="" class="cricle">
        <span class="dot grey"></span>
      </div>
      <div class="chat-message">
        <p>Mick Pole</p>
        <span>Is offline.</span>
        <span class="small">offline</span>
      </div>
    </div>

    <div class="chat-sidebar">
      <div class="chat-img">
        <img src="../static/img/user3.jpg" alt="" class="cricle">
        <span class="dot green"></span>
      </div>
      <div class="chat-message">
        <p>James Tree</p>
        <span>Awaiting your reply.</span>
        <span class="small">online</span>
      </div>
    </div>
  </li>
</ul>
<!-- Left Sidebar -->
<ul id="slide-out-left" class="side-nav collapsible">
  <li>
    <div class="sidenav-header primary-color">
      <div class="nav-social">
        <i class="ion-social-facebook"></i>
        <i class="ion-social-twitter"></i>
        <i class="ion-social-tumblr"></i>
      </div>
      <div class="nav-avatar">
        <img class="circle avatar" src="../static/img/user.jpg" alt="">
        <div class="avatar-body">
          <h3>Halo</h3>
          <p>Material Mobile</p>
        </div>
      </div>
    </div>
  </li>
  <li>
    <div class="collapsible-header">
      <i class="ion-android-home"></i> Home <span class="badge blue lighten-2">5</span>
    </div>
    <div class="collapsible-body">
      <ul class="collapsible">
        <li>
          <a href="index.html">Classic</a>
          <a href="index-sliced.html">Sliced</a>
          <a href="index-slider.html">Slider</a>
          <a href="index-drawer.html">Drawer</a>
          <a href="index-walkthrough.html">Walkthrough</a>
        </li>
      </ul>
    </div>
  </li>
  <li>
    <div class="collapsible-header">
      <i class="ion-android-exit"></i> Layout <span class="badge blue lighten-2">5</span>
    </div>
    <div class="collapsible-body">
      <ul class="collapsible">
        <li>
          <a href="material.html">Material</a>
          <a href="left-sidebar.html">Left</a>
          <a href="right-sidebar.html">Right</a>
          <a href="dual-sidebar.html">Dual</a>
          <a href="blank.html">Blank</a>
        </li>
      </ul>
    </div>
  </li>
  <li>
    <div class="collapsible-header">
      <i class="ion-android-document"></i> Pages <span class="badge blue lighten-2">12</span>
    </div>
    <div class="collapsible-body">
      <ul class="collapsible">
        <li>
          <a href="article.html">Article</a>
          <a href="event.html">Event</a>
          <a href="project.html">Project</a>
          <a href="player.html">Music Player</a>
          <a href="todo.html">ToDo</a>
          <a href="category.html">Category</a>
          <a href="product.html">Product</a>
          <a href="checkout.html">Checkout</a>
          <a href="search.html">Search</a>
          <a href="faq.html">Faq</a>
          <a href="coming-soon.html">Coming Soon</a>
          <a href="404.html">404</a>
        </li>
      </ul>
    </div>
  </li>
  <li>
    <div class="collapsible-header">
      <i class="ion-android-apps"></i> App <span class="badge blue lighten-2">11</span>
    </div>
    <div class="collapsible-body">
      <ul class="collapsible">
        <li>
          <a href="calendar.html">Calendar</a>
          <a href="profile.html">Profile</a>
          <a href="chat.html">Chat</a>
          <a href="login.html">Login</a>
          <a href="signup.html">Sign Up</a>
          <a href="lockscreen.html">Lockscreen</a>
          <a href="forgot.html">Password</a>
          <a href="notification.html">Notification</a>
          <a href="chart.html">Chart</a>
          <a href="timeline.html">Timeline</a>
          <a href="activity.html">Activity</a>
        </li>
      </ul>
    </div>
  </li>
  <li>
    <div class="collapsible-header">
      <i class="ion-android-list"></i> Blog <span class="badge blue lighten-2">2</span>
    </div>
    <div class="collapsible-body">
      <ul class="collapsible">
        <li>
          <a href="blog.html">Classic</a>
          <a href="blog-card.html">Card</a>
        </li>
      </ul>
    </div>
  </li>
  <li>
    <div class="collapsible-header">
      <i class="ion-android-image"></i> Gallery <span class="badge blue lighten-2">3</span>
    </div>
    <div class="collapsible-body">
      <ul class="collapsible">
        <li>
          <a href="gallery-filter.html">Filter</a>
          <a href="gallery-masonry.html">Masonry</a>
          <a href="gallery-card.html">Card</a>
        </li>
      </ul>
    </div>
  </li>
  <li>
    <div class="collapsible-header">
      <i class="ion-android-camera"></i> Portfolio <span class="badge blue lighten-2">3</span>
    </div>
    <div class="collapsible-body">
      <ul class="collapsible">
        <li>
          <a href="portfolio-filter.html">Filter</a>
          <a href="portfolio-masonry.html">Masonry</a>
          <a href="portfolio-card.html">Card</a>
        </li>
      </ul>
    </div>
  </li>
  <li><a href="shop.html" class="no-child"><i class="ion-android-playstore"></i> Shop</a></li>
  <li><a href="video.html" class="no-child"><i class="ion-ios-videocam"></i> Video</a></li>
  <li><a href="news.html" class="no-child"><i class="ion-social-rss"></i> News</a></li>
  <li><a href="contact.html" class="no-child"><i class="ion-android-map"></i> Contact</a></li>
</ul>
<!-- End of Sidebars -->
