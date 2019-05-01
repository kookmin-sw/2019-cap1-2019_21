# requires: Nodejs/NPM, PowerShell
# Permission to run PS scripts (for this session only):
# Set-ExecutionPolicy -ExecutionPolicy Bypass -Scope Process

# exit if cmdlet gives error
$ErrorActionPreference = "Stop"

# Check to see if root CA file exists, download if not
If (!(Test-Path ".\root-CA.crt")) {
    "`nDownloading AWS IoT Root CA certificate from AWS..."
    Invoke-WebRequest -Uri https://www.amazontrust.com/repository/AmazonRootCA1.pem -OutFile root-CA.crt
}

# install AWS Device SDK for NodeJS if not already installed
If (!(Test-Path ".\node_modules")) {
    "`nInstalling AWS SDK..."
    npm install aws-iot-device-sdk
}

"`nRunning pub/sub sample application..."
node .\node_modules\aws-iot-device-sdk\examples\device-example.js --host-name a28jgqlr4nhpzv-ats.iot.us-west-2.amazonaws.com --private-key .\my_farm.private.key --client-certificate .\my_farm.cert.pem --ca-certificate .\root-CA.crt --client-id=sdk-nodejs-4bdbf262-9d55-4445-abd3-d12d47767f3d
