
CREATE TABLE bfmap_t(
  gid bigserial,
  osm_id bigint NOT NULL,
  class_id integer NOT NULL,
  source bigint NOT NULL,
  target bigint NOT NULL,
  length double precision NOT NULL,
  reverse double precision NOT NULL,
  maxspeed_forward integer,
  maxspeed_backward integer,
  priority double precision NOT NULL);

SELECT AddGeometryColumn('bfmap_t','geom',4326, 'LINESTRING', 2);

INSERT into bfmap_t(osm_id, class_id, source, target, length, reverse, maxspeed_forward, maxspeed_backward, priority, geom)
  SELECT
    objectid as osm_id,

    case
      when roadcls_c = 6 then 1
      else 100
    end as class_id,

    case
      when oneway_c = 2 or oneway_c = 4 then tonodeid
      else fromnodeid
    end as source,

    case
      when oneway_c = 2 or oneway_c = 4 then fromnodeid
      else tonodeid
    end as target,

    1 as length,

    case
      when oneway_c = 0 then 1
      else -1
    end as reverse,

    60 as maxspeed_forward,

    60 as maxspeed_backward,

    1 as priority,

    case
      when oneway_c = 2 or oneway_c = 4 then ST_GeometryN(ST_Reverse(the_geom), 1)
      else ST_GeometryN(the_geom, 1)
    end as geom
  from gt_link_turumi;
