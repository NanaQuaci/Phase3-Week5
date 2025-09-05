FROM maven:3.9.6-eclipse-temurin-21

# Set working directory
WORKDIR /app

# Install Node.js and npm
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash - && \
    apt-get install -y nodejs

# Install Appium + drivers
RUN npm install -g appium@2.0.0 appium-doctor allure-commandline --save-dev \
    && appium driver install uiautomator2

# Install Android SDK & tools
RUN apt-get update && apt-get install -y \
    android-sdk adb wget unzip \
    && rm -rf /var/lib/apt/lists/*

# Set Android env vars
ENV ANDROID_HOME=/usr/lib/android-sdk
ENV PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools

# Set display for headless Chrome/Xvfb
ENV DISPLAY=:99

# Pre-fetch Maven dependencies
RUN mvn dependency:go-offline

# Copy project files
COPY . .

# Default command: Run tests + generate Allure report
CMD ["sh", "-c", "Xvfb :99 & mvn clean test && mkdir -p allure-report && allure generate allure-results --clean -o allure-report"]
