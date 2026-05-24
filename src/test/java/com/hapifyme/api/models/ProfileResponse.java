package com.hapifyme.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileResponse  {
    @JsonIgnoreProperties(ignoreUnknown = true)
        private String status;
        private String message;
        private User user;

        // Getters and Setters
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        // Inner User class
        public static class User {
            private String id;

            @JsonProperty("first_name")
            private String firstName;

            private String last_name;
            private String username;
            private String email;
            private String signup_date;
            private String profile_pic;

            // Getters and Setters

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFirstName() {
                return firstName;
            }

            public void setFirst_name(String firstName) {
                this.firstName = firstName;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getSignup_date() {
                return signup_date;
            }

            public void setSignup_date(String signup_date) {
                this.signup_date = signup_date;
            }

            public String getProfile_pic() {
                return profile_pic;
            }

            public void setProfile_pic(String profile_pic) {
                this.profile_pic = profile_pic;
            }
        }
}


