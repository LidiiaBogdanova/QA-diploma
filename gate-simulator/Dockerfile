FROM node:lts-alpine3.12
WORKDIR /opt/app
COPY . .
RUN npm install -g npm@8.10.0
CMD ["npm", "start"]
EXPOSE 9999