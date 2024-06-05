FROM node:16-alpine AS builder

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

ARG REACT_APP_API_GATEWAY_URL

ENV REACT_APP_API_GATEWAY_URL=$REACT_APP_API_GATEWAY_URL

RUN npm run build

FROM nginx

COPY --from=builder /app/build /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf