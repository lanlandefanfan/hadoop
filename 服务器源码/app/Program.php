<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Program extends Model
{

    protected $table='app';
    protected $primaryKey = "id";
    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
		'name','company','app_key','description','starttime','endtime',
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
      'created_at','updated_at',
    ];
    public $timestamps =true;
}
