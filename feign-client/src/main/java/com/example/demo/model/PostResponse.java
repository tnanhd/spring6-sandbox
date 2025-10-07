package com.example.demo.model;

public record PostResponse(
        Post[] posts,
        Integer total,
        Integer skip,
        Integer limit
) {

    public record Post(
            Integer id,
            String title,
            String body,
            String[] tags,
            Reactions reactions,
            Integer views,
            Integer userId
    ) {

        public record Reactions(
                Integer likes,
                Integer dislikes
        ) {
        }
    }
}

