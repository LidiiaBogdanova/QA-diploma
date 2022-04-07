FROM node:erbium-alpine3.12
WORKDIR /src/app
COPY . .
CMD ["npm", "start"]
EXPOSE 9999

FROM mysql:8.0
WORKDIR /src/app
COPY . .
CMD ["java", "-jar", "artifacts/aqa-shop.jar -P:jdbc:mysql://localhost:3306/touristicBooking -P:jdbc.user=app -P:jdbc.password=pass"]
EXPOSE 9999