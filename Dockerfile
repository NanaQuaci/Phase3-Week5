#Dockerfile
FROM maven:3.9.6-eclipse-temurin-21

WORKDIR /app

# Node.js
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash - && \
    apt-get install -y nodejs

# Appium + drivers
RUN npm install -g appium@2.0.0 appium-doctor allure-commandline --save-dev \
    && appium driver install uiautomator2

# Android SDK & tools
RUN apt-get update && apt-get install -y android-sdk adb wget unzip \
    && rm -rf /var/lib/apt/lists/*

ENV ANDROID_HOME=/usr/lib/android-sdk
ENV PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
ENV DISPLAY=:99

# Copy pom first
COPY pom.xml ./

RUN mvn dependency:go-offline

# Copy project files including APK
COPY . .

# Keep container ready but do NOT run tests by default
CMD ["sh", "-c", "echo 'Container ready. Run tests via docker compose or CI.'"]
